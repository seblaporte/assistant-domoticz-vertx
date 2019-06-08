package fr.seblaporte.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import fr.seblaporte.DTO.domoticz.DomoticzDeviceStatus;
import fr.seblaporte.DTO.domoticz.DomoticzDeviceStatusEnum;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomoticzDeviceStatusDeserializer extends JsonDeserializer<DomoticzDeviceStatus> {


    @Override
    public DomoticzDeviceStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        final String domoticzStatus = node.asText();

        if (domoticzStatus.equals("On") || domoticzStatus.equals("Off")) {
            DomoticzDeviceStatusEnum status = domoticzStatus.equals("On") ? DomoticzDeviceStatusEnum.ON : DomoticzDeviceStatusEnum.OFF;
            return new DomoticzDeviceStatus(status);
        } else if (domoticzStatus.equals("Open") || domoticzStatus.equals("Closed")) {
            DomoticzDeviceStatusEnum status = domoticzStatus.equals("Open") ? DomoticzDeviceStatusEnum.OPEN : DomoticzDeviceStatusEnum.CLOSED;
            return new DomoticzDeviceStatus(status);
        } else {

            Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");
            Matcher matcher = pattern.matcher(domoticzStatus);

            if (matcher.matches()) {
                String levelValue = matcher.group(2);
                Integer level = Integer.parseInt(levelValue);
                return new DomoticzDeviceStatus(DomoticzDeviceStatusEnum.ON, level);
            }
        }

        return new DomoticzDeviceStatus(DomoticzDeviceStatusEnum.OFF);
    }
}
