package service;

import com.chrystian.screenmatch.exception.ErroDeConversaoDeJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConverteDados implements IConverteDados {
    private static final Logger log = LoggerFactory.getLogger(ConverteDados.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter JSON para {}. Detalhes: {}", classe.getSimpleName(), e.getMessage());
            throw new ErroDeConversaoDeJsonException("Erro ao tentar converter o JSON para a classe " + classe.getSimpleName() + ": " + e.getMessage());
        }
    }
}
