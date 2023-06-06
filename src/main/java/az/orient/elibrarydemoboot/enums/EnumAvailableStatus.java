package az.orient.elibrarydemoboot.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumAvailableStatus {

    ACTIVE(1),
    DEACTIVE(0),
    INPROGRESS(2),
    SENDED(3);

    public int value;

}
