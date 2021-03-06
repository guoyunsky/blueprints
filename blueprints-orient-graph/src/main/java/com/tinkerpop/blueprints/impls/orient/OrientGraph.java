package com.tinkerpop.blueprints.impls.orient;

import org.apache.commons.configuration.Configuration;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.tinkerpop.blueprints.Features;

/**
 * A Blueprints implementation of the graph database OrientDB (http://www.orientechnologies.com)
 *
 * @author Luca Garulli (http://www.orientechnologies.com)
 */
public class OrientGraph extends OrientTransactionalGraph {
    protected final Features FEATURES = new Features();

    /**
     * Constructs a new object using an existent OGraphDatabase instance.
     *
     * @param iDatabase Underlying OGraphDatabase object to attach
     */
    public OrientGraph(final ODatabaseDocumentTx iDatabase) {
        super(iDatabase);
        config();
    }

    public OrientGraph(final String url) {
        super(url, ADMIN, ADMIN);
        config();
    }

    public OrientGraph(final String url, final String username, final String password) {
        super(url, username, password);
        config();
    }

    public OrientGraph(final Configuration configuration) {
        this(configuration.getString("blueprints.orientdb.url", null),
             configuration.getString("blueprints.orientdb.username", null),
             configuration.getString("blueprints.orientdb.password", null));
    }

    public Features getFeatures() {
        // DYNAMIC FEATURES BASED ON CONFIGURATION
        FEATURES.supportsEdgeIndex = !useLightweightEdges;
        FEATURES.supportsEdgeKeyIndex = !useLightweightEdges;
        FEATURES.supportsEdgeIteration = !useLightweightEdges;
        FEATURES.supportsEdgeRetrieval = !useLightweightEdges;
        return FEATURES;
    }

    protected void config() {
        FEATURES.supportsDuplicateEdges = true;
        FEATURES.supportsSelfLoops = true;
        FEATURES.isPersistent = true;
        FEATURES.supportsVertexIteration = true;
        FEATURES.supportsVertexIndex = true;
        FEATURES.ignoresSuppliedIds = true;
        FEATURES.supportsTransactions = true;
        FEATURES.supportsVertexKeyIndex = true;
        FEATURES.supportsKeyIndices = true;
        FEATURES.isWrapper = false;
        FEATURES.supportsIndices = true;
        FEATURES.supportsVertexProperties = true;
        FEATURES.supportsEdgeProperties = true;

        // For more information on supported types, please see:
        // http://code.google.com/p/orient/wiki/Types
        FEATURES.supportsSerializableObjectProperty = true;
        FEATURES.supportsBooleanProperty = true;
        FEATURES.supportsDoubleProperty = true;
        FEATURES.supportsFloatProperty = true;
        FEATURES.supportsIntegerProperty = true;
        FEATURES.supportsPrimitiveArrayProperty = true;
        FEATURES.supportsUniformListProperty = true;
        FEATURES.supportsMixedListProperty = true;
        FEATURES.supportsLongProperty = true;
        FEATURES.supportsMapProperty = true;
        FEATURES.supportsStringProperty = true;
        FEATURES.supportsThreadedTransactions = true;
    }
}