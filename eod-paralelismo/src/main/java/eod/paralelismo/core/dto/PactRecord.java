package eod.paralelismo.core.dto;

import eod.paralelismo.core.enums.ProcessCommand;
import eod.paralelismo.core.enums.StatusProccess;

public record PactRecord(Long id, String name, ProcessCommand processCommand, StatusProccess statusProccess) {}
