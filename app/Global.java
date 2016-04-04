import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.time.LocalDateTime;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		Logger.info(">>> Aplicacao wgmt inicializada...");
		Logger.info(">>> Hora = " + LocalDateTime.now());
	}

    @Override
	public void onStop(Application app) {
        Logger.info(">>> Aplicacao wgmt finalizando...");
	}

}
