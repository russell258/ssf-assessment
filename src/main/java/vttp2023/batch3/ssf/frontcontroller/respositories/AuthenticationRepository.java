package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	@Autowired
	@Qualifier("resource")
	private RedisTemplate<String,Object> template;
		
	//check if user key is in redis repository. If so, send to View2. And put expiry of this key.
	public void disable(String username){
		template.opsForValue().set(username,"disable", Duration.ofMinutes(30));
	}

	//check if user is disabled before login
	public Optional<String> checkDisable(String username){
		String check = (String) template.opsForValue().get(username);
		if (null==check||check.trim().length()<=0){
			return Optional.empty();
		}
		return Optional.of(username);
	}

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

}
