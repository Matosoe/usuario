package br.com.perfilcar.usuario.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import br.com.perfilcar.perfil.kafka.Producer;
import br.com.perfilcar.usuario.models.Usuario;
import br.com.perfilcar.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioResource {

	
	//private final Producer producer = null;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios(){
		//producer.send("Pedido de Lista de Usuario");
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuarios/{emailUsuario}")
	public List<Usuario> listaUsuariosPorEmail(@PathVariable(value = "emailUsuario") String emailUsuario){
		//producer.send("Pedido de Lista de Usuario por e-mail");
		return usuarioRepository.findByEmailUsuario(emailUsuario);
	}
	
	@GetMapping("/usuario/{idUsuario}")
	public Usuario listausuarioUnico(@PathVariable(value = "idUsuario") long idUsuario){
		//producer.send("Pedido de Lista de Perfil por id");
		return usuarioRepository.findById(idUsuario);
	}
	
	@PostMapping("/usuario")
	public Usuario salvaUsuario(@RequestBody Usuario usuario) {
		//producer.send("Pedido de criação de Perfil");
		return usuarioRepository.save(usuario);
	}

	@PutMapping("/usuario")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
		//producer.send("Pedido de atualização de Perfil");
		return usuarioRepository.save(usuario);
	}

	@DeleteMapping("/usuario")
	 public void deletaUsuario(@RequestBody Usuario usuario) {
		//producer.send("Pedido de deleção de Perfil");
		usuarioRepository.delete(usuario);
	}

}
