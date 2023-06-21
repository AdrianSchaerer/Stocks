package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/*  Die Klasse Json aus der JSON-Java library wird für die Umwandlung von Json zu Javacode verwendet.
    Diese können zu JSON object, array, number, string, boolean or null verwendet werden.
    https://www.baeldung.com/java-org-json
*/
public class Json {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    /* Die Klassenmethode erzeugt ein objectMapper
    * defaultObjectMapper verhindert ein Fehler bei Json, welche nicht der Vorgabe entsprechen */
    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    /* Die Methode parse wandelt ein Json zu einem Ojecttree */
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    // Tree to Value mittels einer Klasse
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }
}
