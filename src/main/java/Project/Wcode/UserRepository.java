package Project.Wcode;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Usuario, Long> {


    List<Usuario> findByNombreUsuario(String name);

    Usuario findByIdUsuarios(long id);
}