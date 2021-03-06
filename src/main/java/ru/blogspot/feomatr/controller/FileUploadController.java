package ru.blogspot.feomatr.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.formBean.FileUploadBean;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * @author iipolovinkin
 * @since 20.09.2015
 */
public class FileUploadController extends SimpleFormController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
    private ClientService clientService;
    private AccountService accountService;

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                                    Object command, BindException errors) throws Exception {

        // cast the bean
        FileUploadBean bean = (FileUploadBean) command;

//        let's see if there's content there
        byte[] file = bean.getFile();
        if (file == null) {
            // hmm, that's strange, the user did not upload anything
        }

        XStream xs = new XStream(new DomDriver());
        try {
            Client client = new Client();
            xs.fromXML(new ByteArrayInputStream(file), client);

            clientService.saveClient(client);
            for (Account account : client.getAccounts()) {
                accountService.saveAccount(account);
            }

        } catch (XStreamException ex) {
            ex.printStackTrace();
        }
        // well, let's do nothing with the bean for now and return
        return super.onSubmit(request, response, command, errors);
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {
        // to actually be able to convert Multipart instance to byte[]
        // we have to register a custom editor
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        // now Spring knows how to handle multipart object and convert them
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}

