package nl.tsuriani.abcltest.infra;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ABCLInit {
	@Inject
	LispExecutor lispExecutor;
	void startup(@Observes StartupEvent event) {
		lispExecutor.loadFromResourceFile("init");
	}
}
