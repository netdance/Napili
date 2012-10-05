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

package org.netdance.napili

class NapiliOutput {


    private static StringWriter sw = new StringWriter();
    private static PrintWriter out = new PrintWriter(sw);

    protected static void flushOutput() {
        out.flush();
        String outputStr = sw.toString();
        Napili.output.setText(Napili.output.getText() + outputStr);
    }

    /**
     * Print a line to the output area
     *
     * @param str String to print
     */
    public static void println(String str) {
        out.println(str);
        System.out.println(str);
    }

    /**
     * Get the PrintWriter which will be used to write to the output area
     *
     * @return PrintWriter used to write to the output area
     */
    public static PrintWriter getPrintWriter() {
        return out;
    }

    protected static void resetOutput() {
        sw = new StringWriter();
        out = new PrintWriter(sw);
    }

}
