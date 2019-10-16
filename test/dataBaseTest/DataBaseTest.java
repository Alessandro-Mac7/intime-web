package dataBaseTest;

import java.util.List;

import org.junit.Test;

import model.Valutazione;
import persistence.DataBaseManager;

public class DataBaseTest {

	@Test
	public void Test() {
		List<Valutazione> val = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
				.findRecensioniRecenti("ale.macri@gmail.com");
		for (Valutazione valutazione : val) {
			System.out.println(valutazione.getData());
		}
	}

}
