package examples;

import com.github.rifttech.jpon.Parser;
import com.github.rifttech.jpon.Parsers;
import com.github.rifttech.jpon.TraversalEvent;
import com.github.rifttech.jpon.descriptor.model.Descriptor;
import examples.utils.ParsingTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class SimpleParsing  extends ParsingTest {


    private static class RowListener implements TraversalEvent.Listener {
        private final Descriptor descriptor;

        private RowListener(Descriptor descriptor) {
            this.descriptor = descriptor;
        }

        @Override
        public void onValue(TraversalEvent event) {
            log.info("event {}", event);
        }

        @Override
        public void onFinalize() {
            log.info("finalize");
        }
    }


    @Test
    public void parseData() throws Exception {
        Descriptor descriptor = getDescriptor("data/00/descriptor.json");
        Parser parser = Parsers.newStreamParser(getSource("data/00/data.json"));
        parser.addListener(new RowListener(descriptor));
        parser.parse();
        parser.close();
    }

}
