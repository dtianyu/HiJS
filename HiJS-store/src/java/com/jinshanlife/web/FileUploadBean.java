/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jinshanlife.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author C0160
 */
@ManagedBean
@RequestScoped
public class FileUploadBean {

    private String destination;
    private String filename;
    private UploadedFile file;

    /**
     * Creates a new instance of FileUploadBean
     */
    public FileUploadBean() {
        destination = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//upload");
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        if (file != null) {
            try {
                save();
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Failure", e.toString());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            setFilename(file.getFileName());
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    private void save() {
        try {
            InputStream in = file.getInputstream();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.setCharacterEncoding("UTF-8");
            OutputStream out = new FileOutputStream(new File(getDestination() + "\\" + file.getFileName()));
            int read = 0;;
            byte[] bytes = new byte[1024];
            while (true) {
                read = in.read(bytes);
                if (read < 0) {
                    break;
                }
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage("Failure", e.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the file
     */
    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

}
