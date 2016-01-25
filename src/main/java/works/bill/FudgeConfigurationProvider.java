package works.bill;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

/**
 * Created by Bill on 24/01/2016.
 */
@RewriteConfiguration
public class FudgeConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public int priority () {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/").to("/index.xhtml"))
                .addRule(Join.path("/thing/{thingID}/").to("/thing.xhtml"))
                .addRule(Join.path("/login/").to("/login.xhtml"))
                .addRule(Join.path("/logout/").to("/logout.xhtml"))
                .addRule(Join.path("/error/").to("/error.xhtml"))
                ;
    }
}
