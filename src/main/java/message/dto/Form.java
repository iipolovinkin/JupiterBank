package message.dto;

import lombok.Value;

@Value
public class Form {
    String contractNumber;
    String counterPartyNumber;
    String depoCode;
}
