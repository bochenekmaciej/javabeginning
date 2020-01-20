package lang;

import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;

class LangService {
    private LangRepository repository;

    LangService(){
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll(){
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }
    Optional<LangDTO> findById(Integer id){
        return repository.findById(id).map(LangDTO::new);
    }
}
