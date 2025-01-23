package finish.process.core.dto;

import finish.process.core.enums.ProcessCommand;
import finish.process.core.enums.StatusProccess;

public class PactDTO {

    private Long id;
    private String name;
    private ProcessCommand processCommand;
    private StatusProccess status;
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

    public StatusProccess getStatus() {
        return status;
    }

    public void setStatus(StatusProccess status) {
        this.status = status;
    }

    public Long getQuantityToBeProcessed() {
        return quantityToBeProcessed;
    }

    public void setQuantityToBeProcessed(Long quantityToBeProcessed) {
        this.quantityToBeProcessed = quantityToBeProcessed;
    }
}
