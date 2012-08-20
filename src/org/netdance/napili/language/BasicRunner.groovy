/*
 * Copyright 2012 by the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package org.netdance.napili.language

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.netdance.napili.Napili
import org.netdance.napili.Turtle

class BasicRunner {

    static def run(Napili napili) {
        String scriptStr = napili.code;
        if (scriptStr == null) scriptStr = ''
        CompilerConfiguration config = new CompilerConfiguration()
        config.setScriptBaseClass("org.netdance.napili.language.TurtleDelegateBaseScript")
        ImportCustomizer ic = new ImportCustomizer();
        ic.addImports('javafx.scene.paint.Color')
        config.addCompilationCustomizers(ic)
        GroovyShell shell = new GroovyShell(config)
        try {
            Script script = shell.parse(scriptStr)
            script.setBinding(new BasicBinding(napili))
            script.run()
        } catch (Exception e) {
            napili.println(e.getMessage())
            e.printStackTrace(System.out)
        }
    }


    private static class BasicBinding extends Binding {

        Turtle turtle
        Napili napili

        public BasicBinding(Napili napili) {
            super();
            turtle = new Turtle(napili)
            this.napili = napili
        }

        public Object getVariable(String name) {
            if (name == 'out') {
                return napili.getPrintWriter();
            }
            if (name == 'turtle') {
                return turtle;
            }

            // treat all no-arg methods on Turtle as binding variables with side effects
            if (turtle.metaClass.respondsTo(turtle, name)) {
                return turtle.metaClass.invokeMethod(this, name, null)
            }
            return super.getVariable(name);
        }

        public void setVariable(String name, Object value) {
            if ("turtle".equals(name)) {
                napili.println('Unable to set "turtle" to value' + value)
                return;
            }
            super.setVariable(name, value);
        }

    }
}