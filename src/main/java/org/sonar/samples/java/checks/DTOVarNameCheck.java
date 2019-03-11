package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;


import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.expression.InstanceOfTreeImpl;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.List;
 
@Rule(
		  key = "YT_DTOFILE_ID_VAR_TYPECHECK",
		  name = "In DTO File, ID Variables should be declared as Long",
		  description = "Whenever declaring ID Variables in DTO file it should be declared as Long Datatype ",
		  priority = Priority.CRITICAL,
		  tags = {"bug"})

public class DTOVarNameCheck extends IssuableSubscriptionVisitor{
 
	  public List<Tree.Kind> nodesToVisit() {
		    // Register to the kind of nodes you want to be called upon visit.
		    return ImmutableList.of(
		    		Tree.Kind.VARIABLE);
		  }

		  @Override
		  public void visitNode(Tree tree) {
			  String fileName = this.context.getFile().getName();
			  fileName = fileName.substring(0, fileName.indexOf("."));
			  String last3charname = fileName != null && fileName.length() > 3 ? fileName.substring(fileName.length() - 3) : fileName;
			  System.out.println("last 3 value " + last3charname);
			  if(last3charname.equalsIgnoreCase("DTO")) {
				  System.out.println("equals DTO");
				  if (tree.is(Tree.Kind.VARIABLE)) {
					  VariableTree variableTree = (VariableTree) tree;
					  String varName = variableTree.simpleName().toString();
					  String last2charvarname = varName != null && varName.length() > 2 ? varName.substring(varName.length() - 2) : varName;
					  if(last2charvarname.equalsIgnoreCase("Id")) {
						  TypeTree type = variableTree.type();
						  String varType =  type.symbolType().toString();
						  if(!varType.equals("Long")) {
							  reportIssue(variableTree, "In DTO File, ID Variables should be declared as Long");
							  System.out.println("Invalid Vtype - Reporting Issue " + type.symbolType().toString() + "\n-----------------------------------------");
						  }
					  }
				  }
			 }
		  }
}
