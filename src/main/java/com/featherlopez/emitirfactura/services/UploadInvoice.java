package com.featherlopez.emitirfactura.services;

import defaultPackage.Response_ws;
import defaultPackage.SERVICESFACTURATECH;
import defaultPackage.SERVICESFACTURATECHPortType;
import defaultPackage.SERVICESFACTURATECH_Impl;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UploadInvoice {

    public Optional<Response_ws> uploadInvoiceFile(String username, String password, byte[] xmlData) {
        // base64 encode xml
        String encoded = Base64.getEncoder().encodeToString(xmlData);
        // transmit and return
        SERVICESFACTURATECH services = new SERVICESFACTURATECH_Impl();
        try {
            SERVICESFACTURATECHPortType servicePortType = services.getSERVICESFACTURATECHPort();
            Response_ws response = servicePortType.ftechActionUploadInvoiceFile(username, password, encoded);
            System.out.println(response);
            return Optional.of(response);
        } catch (RemoteException | ServiceException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        //return Optional.empty();
    }

}
