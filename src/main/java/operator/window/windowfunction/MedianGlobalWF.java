package operator.window.windowfunction;

import model.Lamp;
import utils.median.TDigestMedian;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/*valerio*/

//WindowFunction<input, output, key, window>
public class MedianGlobalWF implements AllWindowFunction<Tuple2<TDigestMedian, Lamp>, Lamp, TimeWindow> {

	private static final long serialVersionUID = 1L;

	@Override
	public void apply(TimeWindow window, Iterable<Tuple2<TDigestMedian, Lamp>> input, Collector<Lamp> out) throws Exception {

		Tuple2<TDigestMedian, Lamp> medianLamp = input.iterator().next();
		Lamp clone = medianLamp.f1.clone();
		clone.setConsumption(medianLamp.f0.getMedian());
		out.collect(clone);
		//out.collect(new Lamp(medianLamp.f1.getLampId(), medianLamp.f0.getMedian(), medianLamp.f1.getCity(), medianLamp.f1.getAddress(), medianLamp.f1.getTimestamp()));
	}

}