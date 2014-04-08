/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.yarcdata.urika.hadoop.rdf.io.input;

import java.io.IOException;
import java.io.Writer;

import org.apache.jena.hadoop.rdf.types.QuadWritable;

import com.hp.hpl.jena.sparql.core.Quad;

/**
 * Abstract tests for Quad input formats
 * @author rvesse
 *
 */
public abstract class AbstractQuadsInputFormatTests extends AbstractNodeTupleInputFormatTests<Quad, QuadWritable> {

    @Override
    protected void generateTuples(Writer writer, int num) throws IOException {
        for (int i = 0; i < num; i++) {
            writer.write("<http://subjects/" + i + "> <http://predicate> \"" + i + "\" <http://graphs/" + i + "> .\n");
        }
        writer.flush();
        writer.close();
    }
    
    @Override
    protected void generateBadTuples(Writer writer, int num) throws IOException {
        for (int i = 0; i < num; i++) {
            writer.write("<http://broken\n");
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void generateMixedTuples(Writer writer, int num) throws IOException {
        boolean bad = false;
        for (int i = 0; i < num; i++, bad = !bad) {
            if (bad) {
                writer.write("<http://broken\n");
            } else {
                writer.write("<http://subjects/" + i + "> <http://predicate> \"" + i + "\" <http://graphs/" + i + "> .\n");
            }
        }
        writer.flush();
        writer.close();
    }

}
