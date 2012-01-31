/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.slf4j.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.logging.LoggerManager;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.slf4j.ILoggerFactory;
import org.sonatype.inject.EagerSingleton;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
@EagerSingleton
@Component(role = Slf4jPlexusInitializer.class, instantiationStrategy = "singleton")
public class Slf4jPlexusInitializer implements Contextualizable
{
   @Inject
   public void setLoggerManager(LoggerManager loggerManager)
   {
      final ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
      if (factory instanceof PlexusLoggerFactory)
      {
         ((PlexusLoggerFactory) factory).setLoggerManager(loggerManager);
      }
   }

   public void contextualize(Context context) throws ContextException
   {
      final Object plexus = context.get(PlexusConstants.PLEXUS_KEY);
      if (plexus instanceof PlexusContainer)
      {
         try
         {
            setLoggerManager(((PlexusContainer) plexus).lookup(LoggerManager.class));
         }
         catch (ComponentLookupException e)
         {
         }
      }
   }
}
