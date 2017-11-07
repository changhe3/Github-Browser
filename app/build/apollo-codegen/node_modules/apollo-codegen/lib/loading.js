"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const fs = require("fs");
const graphql_1 = require("graphql");
const errors_1 = require("./errors");
function loadSchema(schemaPath) {
    if (!fs.existsSync(schemaPath)) {
        throw new errors_1.ToolError(`Cannot find GraphQL schema file: ${schemaPath}`);
    }
    const schemaData = require(schemaPath);
    if (!schemaData.data && !schemaData.__schema) {
        throw new errors_1.ToolError('GraphQL schema file should contain a valid GraphQL introspection query result');
    }
    return graphql_1.buildClientSchema((schemaData.data) ? schemaData.data : schemaData);
}
exports.loadSchema = loadSchema;
function extractDocumentFromJavascript(content, tagName = 'gql') {
    const re = new RegExp(tagName + '\\s*`([^`]*)`', 'g');
    let match;
    const matches = [];
    while (match = re.exec(content)) {
        const doc = match[1]
            .replace(/\${[^}]*}/g, '');
        matches.push(doc);
    }
    const doc = matches.join('\n');
    return doc.length ? doc : null;
}
function loadAndMergeQueryDocuments(inputPaths, tagName = 'gql') {
    const sources = inputPaths.map(inputPath => {
        const body = fs.readFileSync(inputPath, 'utf8');
        if (!body) {
            return null;
        }
        if (inputPath.endsWith('.jsx') || inputPath.endsWith('.js')
            || inputPath.endsWith('.tsx') || inputPath.endsWith('.ts')) {
            const doc = extractDocumentFromJavascript(body.toString(), tagName);
            return doc ? new graphql_1.Source(doc, inputPath) : null;
        }
        return new graphql_1.Source(body, inputPath);
    }).filter(source => source);
    return graphql_1.concatAST(sources.map(source => graphql_1.parse(source)));
}
exports.loadAndMergeQueryDocuments = loadAndMergeQueryDocuments;
//# sourceMappingURL=loading.js.map