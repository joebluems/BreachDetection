package com.breach.drill;

import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.holders.Float8Holder;
import org.apache.drill.exec.expr.holders.IntHolder;

@FunctionTemplate(name = "logLikelihood", scope = FunctionTemplate.FunctionScope.SIMPLE, nulls = FunctionTemplate.NullHandling.NULL_IF_NULL)
public class LogLikelihood implements DrillSimpleFunc {

	@Param
	IntHolder n11;
	@Param
	IntHolder n21;
	@Param
	IntHolder n1t;
	@Param
	IntHolder n2t;
	@Output
	Float8Holder out;

	public void setup() {
	}

	public void eval() {
		float ll = (float) 0.0;
		int n12 = n1t.value - n11.value;
		int n22 = n2t.value - n21.value;
		int nt1 = n11.value + n21.value;
		int nt2 = n12 + n22;
		int nt = nt1 + nt2;

		// calculate LL for non-zero elements
		if (n11.value > 0) {
			ll += n11.value
					* Math.log(n11.value / ((float) n1t.value * nt1 / nt));
		}
		if (n21.value > 0) {
			ll += n21.value
					* Math.log(n21.value / ((float) n2t.value * nt1 / nt));
		}
		if (n12 > 0) {
			ll += (float) n12 * Math.log(n12 / ((float) n1t.value * nt2 / nt));
		}
		if (n22 > 0) {
			ll += (float) n22 * Math.log(n22 / ((float) n2t.value * nt2 / nt));
		}
		//if (n11.value==0) ll=0;
		if (n11.value/ (float)(n11.value+n21.value)<(n12/(float)(n12 + n22))) ll=0;
		out.value = ll;
	}

}
