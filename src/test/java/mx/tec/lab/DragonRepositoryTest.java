package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.tec.lab.entity.Dragon;
import mx.tec.lab.repository.DragonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DragonRepositoryTest {
	@Resource
	private DragonRepository dragonRepository;
	
	@Test
	public void givenDragon_whenSave_thenRetrieveSame() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxes", retrievedDragon.getName());
	}
	
	@Test
	public void givenDragon_whenModify_thenRetrieveModified() {
		Dragon dragon = new Dragon(2, "Paarthurnax");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(2L).orElse(null);
		retrievedDragon.setName("Oda'viig");
		dragonRepository.save(retrievedDragon);

		retrievedDragon = dragonRepository.findById(2L).orElse(null);
		assertEquals("Oda'viig", retrievedDragon.getName());
		
	}
	
	@Test
	public void givenDragon_whenDelete_thenConfirm() {
		Dragon dragon = new Dragon(3, "Alduin");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(3L).orElse(null);
		assertEquals("Alduin", retrievedDragon.getName());
		
		dragonRepository.deleteById(3L);
		
		retrievedDragon = dragonRepository.findById(3L).orElse(null);
		assertEquals(null, retrievedDragon);
	}
	
}
