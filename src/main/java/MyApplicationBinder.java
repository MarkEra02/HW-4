import data.DBM;
import data.interfaces.IDBM;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import repositories.Repositories;
import repositories.interfaces.IRepositories;

public class MyApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(DBM.class).to(IDBM.class);
        bind(Repositories.class).to(IRepositories.class);
    }
}
