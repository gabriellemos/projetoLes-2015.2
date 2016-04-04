import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.time.LocalDateTime;

public class Global extends GlobalSettings {

	/**
	 * Executa operações antes do server da aplicação ligar.
	 */
	@Override
	public void onStart(Application app) {
		Logger.info(">>> Aplicacao wgmt inicializada...");
		Logger.info(">>> Hora = " + LocalDateTime.now());
	}

	/**
	 * Executa operações após o server da aplicação desligar.
	 */
    @Override
	public void onStop(Application app) {
        Logger.info(">>> Aplicacao wgmt finalizando...");
	}

}
