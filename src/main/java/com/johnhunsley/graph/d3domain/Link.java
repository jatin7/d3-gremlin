package com.johnhunsley.graph.d3domain;

/**
 * <p>
 *      id: link id. If not provided uses array index
 *      name: node name. If not provided uses: 'link [link_id]'
 *      tid: id of target node
 *      sid: id of source node
 *      _color: link color, e.g. red, #aa00bb,
 *      _svgAttrs: Object, svg line attributes
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
public class Link {
    private String id;
    private String name;
    private String tid;
    private String sid;
    private String _color;
    private String _svgAttrs;

    public Link() {}

    public Link(final String id) {
        this.id = id;
    }

    public Link(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Link(final String id, final String name, final String tid, final String sid) {
        this.id = id;
        this.name = name;
        this.tid = tid;
        this.sid = sid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public String get_svgAttrs() {
        return _svgAttrs;
    }

    public void set_svgAttrs(String _svgAttrs) {
        this._svgAttrs = _svgAttrs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        return getId().equals(link.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
