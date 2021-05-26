package rot.pot.Interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

@RestRequestLogger
@Interceptor
public class RestRequestLoggerImpl {
    @AroundInvoke
    public Object logRestRequest(InvocationContext ctx)
            throws Exception {
        String className = ctx.getMethod().getDeclaringClass().getName();
        String method = ctx.getMethod().getName();

        System.out.print("Rest method called: " + className + "." + method + "(");
        Object[] params = ctx.getParameters();

        if(params.length > 0){
            System.out.print(Arrays.toString(params)
                    .replace("[", "")
                    .replace("]", ""));
        }

        System.out.println(")");
        return ctx.proceed();
    }
}
