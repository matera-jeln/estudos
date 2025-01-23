package entity.process.core.service;

import entity.process.core.dto.PactRecord;
import org.springframework.stereotype.Service;

@Service
public class EntityProcessService {

    public void process(PactRecord pact) throws InterruptedException {
        System.out.println("Inicio processamento fake do pacto: " + pact.id());
        Thread.sleep(2000);
        System.out.println("Fim processamento fake do pacto: " + pact.id());
        //chamar metodo que cria o producer
    }

    //criar metodo responsavel por produzir uma mensagem de resposta.
}
