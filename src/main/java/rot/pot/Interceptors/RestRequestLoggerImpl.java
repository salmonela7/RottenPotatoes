package rot.pot.Interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@RestRequestLogger
@Interceptor
public class RestRequestLoggerImpl {
    @AroundInvoke
    public Object logRestRequest(InvocationContext ctx)
            throws Exception {
        String className = ctx.getMethod().getDeclaringClass().getName();
        String method = ctx.getMethod().getName();

        System.out.println("Rest method called: " + className + "." + method);
        return ctx.proceed();
    }
}
