package dsw.gerumap.app.serializer;

import com.google.gson.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.lang.reflect.Type;

public class ShapeAdapter implements JsonSerializer<Shape>, JsonDeserializer<Shape> {
    @Override
    public Shape deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String jsonType = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        var typeName = type.getTypeName();
        var pkg = "java.awt.";
        if(jsonType.equals("Float")){
            return context.deserialize(element, Ellipse2D.Float.class);
        }
        if(jsonType.equals("GeneralPath")){
            System.out.println("IM HERE MORTAL");
            return context.deserialize(element, GeneralPath.class);
        }
        System.out.println(pkg+jsonType+" aaaaaaaaaaaaaa");

        try {
            return context.deserialize(element, Class.forName(pkg + jsonType));
        } catch (ClassNotFoundException cnfe) {
            System.out.println(pkg + jsonType + " | " + typeName);
            throw new JsonParseException("Unknown element type: " + jsonType, cnfe);
        }
    }

    @Override
    public JsonElement serialize(Shape shape, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        System.out.println(shape.getClass().getSimpleName());
        result.add("type", new JsonPrimitive(shape.getClass().getSimpleName()));
        result.add("properties", context.serialize(shape, shape.getClass()));

        return result;
    }
}
