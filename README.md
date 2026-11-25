<!-- (This is a comment) INSTRUCTIONS: Go through this page and fill out any **bolded** entries with their correct values.-->

# AND101 Milestone 2 - CineStream

Submitted by:
- Javier Hernandez
- Brandon Vargas
- Seth Servin
- Julian Spindola

Time spent: **8** hours spent in total

## Summary

This document provides a summary of our project building process for our app, **CineStream**, an Android movie discovery application powered by The Movie Database (TMDB) API.

## Milestone Requirements

<!-- Please be sure to change the [ ] to [x] for any features you completed.  If a feature is not checked [x], you might miss the points for that item! -->

The following REQUIRED features are completed:

- [x] Assign features to each member of your group
- [x] Establish a goal time for completing each feature

The following REQUIRED files are included:

- [x] Updated 📄 `project_spec.md`, which contains:
  - [x] App Overview (Milestone 1)
  - [x] App Spec (Milestone 1)
  - [x] Checked off 2+ completed features
  - [x] 2+ Videos/GIFs of build progress

- [x] Our 🎥 Demo Video
  - [x] We have also added the Demo Video Link to the Group Info Form on the course portal.

The following EXTRA features are implemented:

- [x] Added local unit tests (`MovieTest`, `MovieResponseParsingTest`, `FeedStateResolverTest`) covering TMDB model formatting, Gson response parsing, and feed state resolution.
- [x] Configured `BuildConfig.TMDB_API_KEY` loading from `local.properties` (or `TMDB_API_KEY` environment variable) so API keys are never committed to Git.

## 🎥 Demo Video

Here's a video that demos all of the app's implemented features:

<img src='https://i.imgur.com/qv39qCz.gif' title='Video Demo' width='' alt='Video Demo' />

VIDEO created with **Snipping Tool**

## Notes

### Current Build Progress (Barebones App)
- Configured Retrofit, Gson, Glide, RecyclerView, Lifecycle runtime, and `INTERNET` permission.
- Implemented TMDB `/3/trending/movie/week` networking (`TmdbApiService`, `TmdbClient`, `Movie`, `MovieResponse`).
- Built the barebones Trending feed (`MainActivity`, `MovieAdapter`, `FeedStateResolver`, minimal Material card layout) with tap placeholder feedback and long-press bookmark toast confirmation.

### Feature Assignments & Goal Times (Fill in names & target dates)

| Feature / Workstream | Assigned To | Goal Time | Status |
| :--- | :--- | :--- | :--- |
| Barebones Setup, TMDB Networking & Trending Feed | **Julian** | **11/24** | Completed |
| Keyword Search (`search/movie`) & Bottom Navigation | **Brandon** | **11/26** | Pending |
| Movie Details & Cast Screen (`movie/{id}`, `credits`) | **Javier** | **12/1** | Pending |
| Dark Cinema Theme, Gold Accents & Home Wireframe Polish | **Seth** | **12/5** | Pending |
