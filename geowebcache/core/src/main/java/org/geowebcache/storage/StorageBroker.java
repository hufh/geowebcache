package org.geowebcache.storage;

import java.util.Map;
import java.util.Set;
import org.geowebcache.layer.TileLayer;

/** Abstracts and manages the storing of cachable objects and their metadata. */
public interface StorageBroker {

    void addBlobStoreListener(BlobStoreListener listener);

    boolean removeBlobStoreListener(BlobStoreListener listener);

    /** Completely eliminates the cache for the given layer. */
    boolean delete(String layerName) throws StorageException;

    /**
     * Completely deletes the cache for a layer/gridset combination; differs from truncate that the
     * layer doesn't need to have a gridSubset associated for the given gridset at runtime (in order
     * to handle the deletion of a layer's gridsubset)
     *
     * @param layerName
     * @param gridSetId
     * @throws StorageException
     */
    boolean deleteByGridSetId(String layerName, String gridSetId) throws StorageException;

    /**
     * Completely deletes the cache for a layer/parameters combination
     *
     * @param layerName
     * @param parametersId
     * @throws StorageException
     */
    boolean deleteByParametersId(String layerName, String parametersId) throws StorageException;

    /**
     * Completely deletes the cache for a layer/parameters combination
     *
     * @param layerName
     * @param parameters
     * @throws StorageException
     */
    boolean deleteByParameters(String layerName, Map<String, String> parameters)
            throws StorageException;

    boolean rename(String oldLayerName, String newLayerName) throws StorageException;

    boolean delete(TileRange trObj) throws StorageException;

    /**
     * Sets the Resource for the given TileObject from storage
     *
     * @param tileObj TileOpject to set the Resource of
     * @return true if successful, false otherwise
     * @throws StorageException
     */
    boolean get(TileObject tileObj) throws StorageException;

    /**
     * Puts the given TileObject into storage
     *
     * @param tileObj
     * @return
     * @throws StorageException
     */
    boolean put(TileObject tileObj) throws StorageException;

    /** Destroy method for Spring */
    void destroy();

    /**
     * Get an entry from the layer's metadata map
     *
     * @param layerName
     * @param key
     * @return
     */
    String getLayerMetadata(String layerName, String key);

    /**
     * Add/set an entry in the layer's metadata map
     *
     * @param layerName
     * @param key
     * @return
     */
    void putLayerMetadata(String layerName, String key, String value);

    boolean getTransient(TileObject tile);

    void putTransient(TileObject tile);

    /**
     * Get the set of parameter IDs cached for the given layer
     *
     * @param layerName
     * @param key
     * @return
     */
    Set<String> getCachedParameterIds(String layerName) throws StorageException;

    /**
     * Get the set of map cached for the given layer, for those parameterizations that have reverse
     * mappings (Created by GWC 1.12 or later)
     *
     * @param layerName
     * @return
     */
    Set<Map<String, String>> getCachedParameters(String layerName) throws StorageException;

    /**
     * Purge parameter caches from the layer if they are unreachable by its current parameter
     * filters. The store may purge gridsets and formats as well. These additional purges may be
     * guaranteed in future.
     *
     * @param layer
     * @return
     * @throws StorageException
     */
    boolean purgeOrphans(final TileLayer layer) throws StorageException;
}
