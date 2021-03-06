package Project.Wcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.lang.Integer.parseInt;
@CrossOrigin(origins = "http://localhost:3000")
@RestController // This means that this class is a Controller
@RequestMapping(path="/data") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;
    MainController(UserRepository repository) {
        this.userRepository = repository;
    }
    @GetMapping(path="/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
    @PostMapping(path="/register") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            ,@RequestParam long phone, @RequestParam String email,@RequestParam String pass) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Usuario n = new Usuario();
        n.setNombreUsuario(name);
        n.setTelefono(phone);
        n.setEmail(email);
        n.setContrasena(pass);
        userRepository.save(n);
        return "Saved";

    }
    @PostMapping(path="/login")
    public @ResponseBody boolean login(@RequestParam String name,@RequestParam String pass) {
        // This returns a JSON or XML with the users
        Iterable<Usuario> users = userRepository.findAll();
        for (Usuario user:users){
            if(user.getNombreUsuario().equals(name)&&user.getContrasena().equals(pass)){
                return true;
            }
        }
        return false;
    }
    @PostMapping(path="/find")
    public @ResponseBody Iterable<Usuario> findUsersByName(@RequestParam String name) {
        // This returns a JSON or XML with the users
        return userRepository.findByNombreUsuario(name);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Usuario> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/user/{idUsuarios}")
    public @ResponseBody Usuario getUser(@PathVariable String idUsuarios) {
        // This returns a JSON or XML with the users
        return userRepository.findByIdUsuarios(parseInt(idUsuarios));
    }

    @PutMapping("/update/{idUsuarios}")
    public @ResponseBody String updateUser (@PathVariable String idUsuarios, @RequestBody Usuario usuarioUpdate) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

       /*return userRepository.findById(Long.parseLong(idUsuarios))
                .map(usuario -> {
                    usuario.setNombreUsuario(usuarioUpdate.getNombreUsuario());
                    usuario.setTelefono(usuarioUpdate.getTelefono());
                    usuario.setEmail(usuarioUpdate.getEmail());
                    usuario.setContrasena(usuarioUpdate.getContrasena());
                    userRepository.save(usuario);
                    return "Saved";
                })
                .orElseGet(() -> {
                    usuarioUpdate.setIdUsuarios(Long.parseLong(idUsuarios));
                    userRepository.save(usuarioUpdate);
                    return idUsuarios;
                });*/
        Usuario usuario;
        usuario = userRepository.findById(Long.parseLong(idUsuarios))
                .orElseGet(()->{
                    usuarioUpdate.setIdUsuarios(Long.parseLong(idUsuarios));
                    return usuarioUpdate;
                 });
        usuario.setNombreUsuario(usuarioUpdate.getNombreUsuario());
        usuario.setTelefono(usuarioUpdate.getTelefono());
        usuario.setEmail(usuarioUpdate.getEmail());
        usuario.setContrasena(usuarioUpdate.getContrasena());
        userRepository.save(usuario);
        return "Updated!";
       /* Usuario n = new Usuario();
        n.setIdUsuarios(idUsuarios);
        n.setNombreUsuario(name);
        n.setTelefono(phone);
        n.setEmail(email);
        n.setContrasena(pass);
        userRepository.save(n);
        return "Saved";*/
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUserById(@PathVariable String id){
        userRepository.delete(userRepository.findByIdUsuarios(parseInt(id)));
        return "Deleted id: "+id;
    }

    @PostMapping(path="/delete")
    public @ResponseBody String deleteUser(@RequestParam String id)
    {
        userRepository.delete(userRepository.findByIdUsuarios(parseInt(id)));
        return "Deleted succesfully";
    }
}