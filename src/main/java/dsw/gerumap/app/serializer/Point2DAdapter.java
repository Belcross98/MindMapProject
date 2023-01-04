package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.maprepository.implementation.Project;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.lang.reflect.Type;

public class Point2DAdapter implements JsonSerializer<Point2D>,JsonDeserializer<Point2D> {

    @Override
    public Point2D deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement element = jsonObject.get("properties");

        var typeName = type.getTypeName();
        var pkg = "dsw.gerumap.app.serializer.";


        try {
            return ((SerializablePoint2D) context.deserialize(element, Class.forName(pkg + "SerializablePoint2D"))).toPoint2D();
        } catch (ClassNotFoundException classNotFound) {
            System.out.println(pkg + "Color" + " | " + typeName);
            throw new JsonParseException("Unknown element type: " + "Color", classNotFound);
        }
    }

    @Override
    public JsonElement serialize(Point2D colorInput, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        var toSerialize = new SerializablePoint2D(colorInput);
        result.add("type", new JsonPrimitive(toSerialize.getClass().getSimpleName()));
        result.add("properties", context.serialize(toSerialize, toSerialize.getClass()));

        return result;
    }


}
