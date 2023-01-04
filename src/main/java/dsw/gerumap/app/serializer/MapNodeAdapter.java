package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.gui.swing.grapheditor.model.Link;
import dsw.gerumap.app.gui.swing.grapheditor.model.Title;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;


import java.lang.reflect.Type;

public class MapNodeAdapter implements JsonSerializer<MapNode>, JsonDeserializer<MapNode> {
    @Override
    public MapNode deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String jsonType = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");


        switch (jsonType){

            case "Title":{
                return context.deserialize(element, Title.class);
            }

            case "Link":{
                return  context.deserialize(element, Link.class);
            }

            case "MindMap":{
                return  context.deserialize(element, MindMap.class);
            }
            case "Project":{
                return context.deserialize(element, Project.class);
            }
            case "ProjectExplorer":{
                return context.deserialize(element, ProjectExplorer.class);
            }
            default:{
                System.out.println("Didn't find");
                break;
            }

        }


//
//        var isGraphElement = !jsonType.equals("Element") && jsonType.contains("Element");
//        var pkg = isGraphElement ? "dsw.gerumap.app.gui.swing.grapheditor.model." : "dsw.gerumap.app.maprepository.implementation.";
//
//        try {
//            return context.deserialize(element, Class.forName(pkg + jsonType));
//        } catch (ClassNotFoundException cnfe) {
//            throw new JsonParseException("Unknown element type: " + jsonType, cnfe);
//        }
        return null;
    }

    @Override
    public JsonElement serialize(MapNode mapNode, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(mapNode.getClass().getSimpleName()));
        result.add("properties", context.serialize(mapNode, mapNode.getClass()));

        return result;
    }
}
