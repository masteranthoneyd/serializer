package com.yangbingdong.serializer;

import com.yangbingdong.serializer.pojo.MessageObj;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author ybd
 * @date 18-9-28
 * @contact yangbingdong1994@gmail.com
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 3)
@Measurement(iterations = 3, time = 3)
@Fork(1)
@Threads(8)
public class SerializeBenchmarkSingleTest {

	private SerializeTest t = new SerializeTest();

	private MessageObj pojoBean = t.pojoBean;

	@Benchmark
	public byte[] testFSTSerializeV2() {
		return t.fSTSerializer.serialize(pojoBean);
	}

	@Benchmark
	public MessageObj testFSTDeSerializeV2() {
		return t.fSTSerializer.deserialize(t.fSTSerializeBytes, MessageObj.class);
	}

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder().include(SerializeBenchmarkSingleTest.class.getSimpleName())
											  .build();
		new Runner(options).run();
	}
}
