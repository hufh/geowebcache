package org.geowebcache.config;

import org.geowebcache.grid.GridSet;
import org.geowebcache.grid.GridSetBroker;

import java.io.IOException;
import java.util.List;

/**
 * Provides access to an (initially empty) GWC configuration.
 */
public abstract class GWCConfigIntegrationTestSupport {

    GridSetBroker broker = new GridSetBroker(true, true);

    /**
     * Resets to an empty configuration;
     */
    public abstract void resetConfiguration() throws Exception;

    public abstract List<TileLayerConfiguration> getTileLayerConfigurations();

    public GridSetBroker getGridSetBroker() {
        return broker;
    }

    public abstract ServerConfiguration getServerConfiguration();

    public abstract GridSetConfiguration getGridSetConfiguration();

    public abstract BlobStoreConfigurationCatalog getBlobStoreConfiguration();
}