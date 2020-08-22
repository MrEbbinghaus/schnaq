import * as d3 from "d3";

class SchnaqD3 {
  constructor(parentId, data) {
    let that = this;
    this.parentId = parentId;
    this.data = data;
    this.width = 800;
    this.height = 600;
    this.color = d3.scaleOrdinal(d3.schemeCategory10);

    this.label = {
      'nodes': [],
      'links': []
    };

    this.adjlist = [];

    data.nodes.forEach((node, index) => {
      this.label.nodes.push({node: node});
      this.label.nodes.push({node: node});
      this.label.links.push({
        source: index * 2,
        target: index * 2 + 1
      });
    });

    this.labelLayout = d3.forceSimulation(this.label.nodes)
      .force("charge", d3.forceManyBody().strength(-50))
      .force("link", d3.forceLink(this.label.links).distance(0).strength(2));

    this.svg = d3.select(parentId).attr("width", this.width).attr("height", this.height);
    this.container = this.svg.append("g");
    this.svg.call(
      d3.zoom()
        .scaleExtent([.1, 4])
        .on("zoom", () => {
          that.container.attr("transform", d3.event.transform);
        })
    );

    this.node = this.container.append("g").attr("class", "nodes")
      .selectAll("g")
      .data(data.nodes)
      .enter()
      .append("circle")
      .attr("r", 5)
      .attr("fill", node => {
        return this.color(node.group);
      })

    this.link = this.container.append("g").attr("class", "links")
      .selectAll("line")
      .data(data.links)
      .enter()
      .append("line")
      .attr("stroke", "#aaa")
      .attr("stroke-width", "1px");

    this.labelNode = this.container.append("g").attr("class", "labelNodes")
      .selectAll("text")
      .data(this.label.nodes)
      .enter()
      .append("text")
      .text((node, index) => {
        return index % 2 === 0 ? "" : node.node.id;
      })
      .style("fill", "#555")
      .style("font-family", "Arial")
      .style("font-size", 12)
      .style("pointer-events", "none"); // to prevent mouseover/drag capture

    data.links.forEach(link => {
      this.adjlist[link.source.index + "-" + link.target.index] = true;
      this.adjlist[link.target.index + "-" + link.source.index] = true;
    });


    this.graphLayout = d3.forceSimulation(data.nodes)
      .force("charge", d3.forceManyBody().strength(-3000))
      .force("center", d3.forceCenter(this.width / 2, this.height / 2))
      .force("x", d3.forceX(this.width / 2).strength(1))
      .force("y", d3.forceY(this.height / 2).strength(1))
      .force("link", d3.forceLink(data.links).id(d => {
        return d.id;
      }).distance(50).strength(1))
      .on("tick", () => {
        that.ticked(that)
      });
    // Note: everything that ticked calls from `that` should be defined before.

    this.node.on("mouseover", () => {
      that.focus(that)
    }).on("mouseout", () => {
      that.unfocus(that)
    });

    this.node.call(
      d3.drag()
        .on("start", d => {
          that.dragstarted(that, d)
        })
        .on("drag", that.dragged)
        .on("end", d => {
          that.dragended(that, d)
        })
    );

  }

  neigh(a, b) {
    return a === b || this.adjlist[a + "-" + b];
  }

  ticked(that) {
    that.node.call(node => {
      that.updateNode(that, node)
    });
    that.link.call(link => {
      that.updateLink(that, link)
    });

    that.labelLayout.alphaTarget(0.3).restart();
    that.labelNode.each(function (d, i) {
      if (i % 2 === 0) {
        d.x = d.node.x;
        d.y = d.node.y;
      } else {
        let b = this.getBBox();

        let diffX = d.x - d.node.x;
        let diffY = d.y - d.node.y;

        let dist = Math.sqrt(diffX * diffX + diffY * diffY);

        let shiftX = b.width * (diffX - dist) / (dist * 2);
        shiftX = Math.max(-b.width, Math.min(0, shiftX));
        let shiftY = 16;
        this.setAttribute("transform", "translate(" + shiftX + "," + shiftY + ")");
      }
    });
    that.labelNode.call(node => {
      that.updateNode(that, node)
    });
  }

  setSize(width, height) {
    this.width = width;
    this.height = height;
    d3.select(this.parentId).attr("width", this.width).attr("height", this.height);
    this.graphLayout = this.graphLayout
      .force("center", d3.forceCenter(this.width / 2, this.height / 2))
      .force("x", d3.forceX(this.width / 2).strength(1))
      .force("y", d3.forceY(this.height / 2).strength(1))
      .on("tick", () => {
        this.ticked(this)
      });
  }

  fixna(coord) {
    if (isFinite(coord)) return coord;
    return 0;
  }

  focus(that) {
    if (d3.event) {
      let index = d3.select(d3.event.target).datum().index;
      that.node.style("opacity", link => {
        return that.neigh(index, link.index) ? 1 : 0.1;
      });
      that.labelNode.attr("display", link => {
        return that.neigh(index, link.node.index) ? "block" : "none";
      });
      that.link.style("opacity", link => {
        return link.source.index === index || link.target.index === index ? 1 : 0.1;
      });
    }
  }

  unfocus(that) {
    that.labelNode.attr("display", "block");
    that.node.style("opacity", 1);
    that.link.style("opacity", 1);
  }

  updateLink(that, link) {
    link.attr("x1", link => {
      return that.fixna(link.source.x);
    })
      .attr("y1", link => {
        return that.fixna(link.source.y);
      })
      .attr("x2", link => {
        return that.fixna(link.target.x);
      })
      .attr("y2", link => {
        return that.fixna(link.target.y);
      });
  }

  updateNode(that, node) {
    node.attr("transform", node => {
      return "translate(" + that.fixna(node.x) + "," + that.fixna(node.y) + ")";
    });
  }

  dragstarted(that, d) {
    d3.event.sourceEvent.stopPropagation();
    if (!d3.event.active) that.graphLayout.alphaTarget(0.3).restart();
    d.fx = d.x;
    d.fy = d.y;
  }

  dragged(d) {
    d.fx = d3.event.x;
    d.fy = d3.event.y;
  }

  dragended(that, d) {
    if (!d3.event.active) that.graphLayout.alphaTarget(0);
    d.fx = null;
    d.fy = null;
  }

}

export {SchnaqD3};