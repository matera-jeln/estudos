package entity.process.core.dto;

import entity.process.core.enums.ProcessCommand;
import entity.process.core.enums.StatusProcess;

public class PactDTO {

    private Long id;
    private String name;
    private ProcessCommand processCommand;
    private StatusProcess status;
    private Long quantityToBeProcessed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProcessCommand getProcessCommand() {
        return processCommand;
    }

    public void setProcessCommand(ProcessCommand processCommand) {
        this.processCommand = processCommand;
    }

    public StatusProcess getStatus() {
        return status;
    }

    public void setStatus(StatusProcess status) {
        this.status = status;
    }

    public Long getQuantityToBeProcessed() {
        return quantityToBeProcessed;
    }

    public void setQuantityToBeProcessed(Long quantityToBeProcessed) {
        this.quantityToBeProcessed = quantityToBeProcessed;
    }
}
