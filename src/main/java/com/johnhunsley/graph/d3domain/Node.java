package com.johnhunsley.graph.d3domain;

/**
 * <p>
 *      id: node id. If not provided uses array index
 *      name: node name. If not provided uses: 'node [node_id]'
 *      _color: node color, e.g. red, #aa00bb,
 *      _cssClass: node css class name
 *      _labelClass: node label css class name
 *      svgSym: node icon, svg document (only works in svg renderer)
 *      _size : node size (svg renderer only)
 *      _width: node width (svg renderer only)
 *      _height: node height (svg renderer only)
 *      _svgAttrs: Object, svg node attributes
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
public class Node {
    private String id;
    private String name;
    private String _color;
    private String _labelClass;
    private String _cssClass;
    private String svgSym;
    private int _size;
    private int _width;
    private int _height;
    private String _svgAttrs;

    public Node() {}

    public Node(final String id) {
        this.id = id;
    }

    public Node(final String id, final String name) {
        this.id = id;
        this.name = name;
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

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public String get_labelClass() {
        return _labelClass;
    }

    public void set_labelClass(String _labelClass) {
        this._labelClass = _labelClass;
    }

    public String get_cssClass() {
        return _cssClass;
    }

    public void set_cssClass(String _cssClass) {
        this._cssClass = _cssClass;
    }

    public String getSvgSym() {
        return svgSym;
    }

    public void setSvgSym(String svgSym) {
        this.svgSym = svgSym;
    }

    public int get_size() {
        return _size;
    }

    public void set_size(int _size) {
        this._size = _size;
    }

    public int get_width() {
        return _width;
    }

    public void set_width(int _width) {
        this._width = _width;
    }

    public int get_height() {
        return _height;
    }

    public void set_height(int _height) {
        this._height = _height;
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
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return getId().equals(node.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
