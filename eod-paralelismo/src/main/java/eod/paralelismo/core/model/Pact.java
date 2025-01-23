package eod.paralelismo.core.model;

import eod.paralelismo.core.enums.ProcessCommand;
import eod.paralelismo.core.enums.StatusProccess;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PACT")
public class Pact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProcessCommand processCommand;

    @Enumerated(EnumType.STRING)
    private StatusProccess status;

    @Transient
    private Integer quantityToBeProcessed;

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

    public Integer getQuantityToBeProcessed() {
        return quantityToBeProcessed;
    }

    public void setQuantityToBeProcessed(Integer quantityToBeProcessed) {
        this.quantityToBeProcessed = quantityToBeProcessed;
    }
}
