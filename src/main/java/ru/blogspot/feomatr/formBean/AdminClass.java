package ru.blogspot.feomatr.formBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iipolovinkin
 * @since 30.03.2015
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminClass {
    private static final Logger log = LoggerFactory.getLogger(AdminClass.class);

    private Integer clientsCount = 0;
    private Integer accountsCount = 0;
    private Integer transfersCount = 0;
    private Integer threadsCount = 0;
    private String attr05 = "";
    private String attr06 = "";
    private String attr07 = "";
    private String attr08 = "";
    private String attr09 = "";
    private String attr10 = "";

    /**
     * Return entity with filled fields clientsCount, accountsCount, transfersCount, threadsCount
     * @param clientsCount
     * @param accountsCount
     * @param transfersCount
     * @param threadsCount
     * @return
     */
    public static AdminClass createAdminFromCATT(int clientsCount, int accountsCount, int transfersCount, int threadsCount) {
        AdminClass entity = new AdminClass();
        entity.clientsCount = clientsCount;
        entity.accountsCount = accountsCount;
        entity.transfersCount = transfersCount;
        entity.threadsCount = threadsCount;
        return entity;
    }

}
