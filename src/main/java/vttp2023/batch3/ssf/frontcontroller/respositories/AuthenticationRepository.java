package vttp2023.batch3.ssf.frontcontroller.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	@Autowired
	@Qualifier("resource")
	private RedisTemplate<String,Object> template;
	
	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

}
