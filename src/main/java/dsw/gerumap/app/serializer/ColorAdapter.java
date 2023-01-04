package dsw.gerumap.app.serializer;

import com.google.gson.*;

import java.awt.*;
import java.lang.reflect.Type;

public class ColorAdapter implements JsonSerializer<Color>, JsonDeserializer<Color> {
    @Override
    public Color deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement element = jsonObject.get("properties");

        var typeName = type.getTypeName();
        var pkg = "dsw.gerumap.app.serializer.";


        try {
            return ((SerializableColor) context.deserialize(element, Class.forName(pkg + "SerializableColor"))).ToAwtColor();
        } catch (ClassNotFoundException classNotFound) {
            System.out.println(pkg + "Color" + " | " + typeName);
            throw new JsonParseException("Unknown element type: " + "Color", classNotFound);
        }
    }

    @Override
    public JsonElement serialize(Color colorInput, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        var toSerialize = new SerializableColor(colorInput);
        result.add("type", new JsonPrimitive(toSerialize.getClass().getSimpleName()));
        result.add("properties", context.serialize(toSerialize, toSerialize.getClass()));

        return result;
    }
}
