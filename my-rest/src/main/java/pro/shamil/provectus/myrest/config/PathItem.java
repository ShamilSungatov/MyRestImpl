package pro.shamil.provectus.myrest.config;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by regal on 18.02.17.
 */
public class PathItem {
    private String path;
    private List<HandlerEnum> type;

    public PathItem(String path, HandlerEnum type) {
        this.path = path;
        this.type = new ArrayList<HandlerEnum>(){{add(type);}};
    }

    public PathItem(String path) {
        this.path = path;
        this.type = new ArrayList<HandlerEnum>();
    }

    public PathItem(PathItem key) {
        this.path = key.getPath();
        this.type = key.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathItem)) return false;

        PathItem pathItem = (PathItem) o;

        if (!path.equals(pathItem.path)) return false;
        return type.equals(pathItem.type);
    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<HandlerEnum> getType() {
        return type;
    }

    public void appendMainPath(String s) {
        StringBuilder sb = new StringBuilder();
        if (s!= null && !s.isEmpty()) {
            sb.append(s);
            if (path != null && !path.isEmpty())
                sb.append("/");
        }
        sb.append(this.path);
        this.path = sb.toString();
    }
}
