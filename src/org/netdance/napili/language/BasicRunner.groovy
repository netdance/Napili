/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
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
                return turtle."$name"()
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