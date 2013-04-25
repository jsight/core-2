/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.container.addons;

import org.jboss.forge.container.impl.AddonImpl;
import org.jboss.forge.container.util.Callables;
import org.jboss.forge.container.util.Visitor;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 */
public class StopDirtyAddonsVisitor implements Visitor<Addon>
{
   private AddonTree tree;

   public StopDirtyAddonsVisitor(AddonTree tree)
   {
      this.tree = tree;
   }

   @Override
   public void visit(Addon instance)
   {
      if (instance instanceof AddonImpl)
      {
         AddonImpl addon = (AddonImpl) instance;
         if (addon.isDirty())
         {
            Callables.call(new StopAddonCallable(tree, addon));
         }
      }
   }

}