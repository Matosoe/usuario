package br.com.perfilcar.usuario.resources;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.perfilcar.usuario.models.Usuario;
import br.com.perfilcar.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioResource {

	String topicName = "PerfilCarLog";
	String key = "Usuario";
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios(){ 
		  String value = "Pedido de Lista de Usuario" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		return usuarioRepository.findAll();
	}

	@GetMapping("/usuarios/{emailUsuario}")
	public List<Usuario> listaUsuariosPorEmail(@PathVariable(value = "emailUsuario") String emailUsuario){
		  String value = "Pedido de Lista de Usuario por e-mail" + "-" + emailUsuario + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();
		return usuarioRepository.findByEmailUsuario(emailUsuario);
	}
	
	@GetMapping("/usuario/{idUsuario}")
	public Usuario listausuarioUnico(@PathVariable(value = "idUsuario") long idUsuario){
		  String value = "Pedido de Lista de Usuario por ID" + "-" + idUsuario + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		return usuarioRepository.findById(idUsuario);
	}
	
	@PostMapping("/usuario")
	public Usuario salvaUsuario(@RequestBody Usuario usuario) {
		  String value = "Pedido de criacao de Usuario" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		return usuarioRepository.save(usuario);
	}

	@PutMapping("/usuario")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
		  String value = "Pedido de atualizacao de Usuario" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();


		return usuarioRepository.save(usuario);
	}

	@DeleteMapping("/usuario")
	 public void deletaUsuario(@RequestBody Usuario usuario) {
		  String value = "Pedido de delecao de usuario" + "-" + ZonedDateTime.now().toString();
      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();
		
		usuarioRepository.delete(usuario);
	}

}